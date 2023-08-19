package main;

import exception.DuplicateEmailException;
import exception.UserNotFoundException;
import jpa.EMF;
import model.User;
import service.ChangeNameService;
import service.JoinService;
import service.UserGetService;
import service.UserWithDrawService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

public class Main {

    private static JoinService joinService = new JoinService();
    private static UserGetService userGetService = new UserGetService();
    private static ChangeNameService changeNameService = new ChangeNameService();
    private static UserWithDrawService withDrawService = new UserWithDrawService();


    public static void main(String[] args) throws IOException {
        EMF.init();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("명령어를 입력하세요");
            String input = bufferedReader.readLine();
            String command[] = input.split(" ");
            if(command[0].equalsIgnoreCase("exit")){
                System.out.println("종료합니다");
                break;
            }
            else if(command[0].equalsIgnoreCase("join")){
                handleJoinCommand(command);
            }
            else if(command[0].equalsIgnoreCase("view")){
                handleViewCommand(command);
            }
            else if(command[0].equalsIgnoreCase("list")){
                handleListCommand(command);
            }
            else if(command[0].equalsIgnoreCase("changename")){
                handleChangeNameCommand(command);
            }
            else if(command[0].equalsIgnoreCase("withdraw")){
                handleWithdrawCommand(command);
            }
            else{
                System.out.println("올바른 명령어를 입력하세요");
            }
            System.out.println("--------------");
        }
        EMF.close();
    }

    public static void handleJoinCommand(String[] command){
        if(command.length != 3){
            System.out.println("명령어가 올바르지 않습니다");
            System.out.println("join 이메일 이름");
            return;
        }
        try {
            User user = new User(command[1],command[2], new Date());
            System.out.println(user.getCreateDate().toString());
            joinService.join(user);
        }catch (DuplicateEmailException e){
            System.out.println("이미 같은 이메일을 가진 사용자가 존재합니다");
        }
    }

    public static void handleViewCommand(String[] command){
        if(command.length != 2){
            System.out.println("명령어가 올바르지 않습니다");
            System.out.println("view 이메일");
            return;
        }
        Optional<User> optionalUser = userGetService.getUser(command[1]);
        if(optionalUser.isPresent()){
            System.out.println(optionalUser.get());
        }
        else{
            System.out.println("해당 유저는 존재하지 않습니다.");
        }
    }

    public static void handleListCommand(String[] command){
        if(command.length != 1){
            System.out.println("명령어가 올바르지 않습니다");
            System.out.println("list");
            return;
        }
        List<User> list = userGetService.getUsers();
        list.forEach(user -> System.out.println(user));
    }

    public static void handleChangeNameCommand(String[] command){
        if(command.length != 3){
            System.out.println("명령어가 올바르지 않습니다");
            System.out.println("changename 이메일 새이름");
            return;
        }
        try {
            changeNameService.changeNameService(command[1],command[2]);
            System.out.println("이름 변경함");
        }catch (UserNotFoundException e){
            System.out.println("해당 유저는 존재하지 않습니다.");
        }
    }

    public static void handleWithdrawCommand(String[] command){
        if(command.length != 2){
            System.out.println("명령어가 올바르지 않습니다");
            System.out.println("withdraw 이메일");
            return;
        }
        try {
            withDrawService.withdrawUser(command[1]);
            System.out.println("탈퇴 처리함");
        }catch (UserNotFoundException e){
            System.out.println("해당 유저는 존재하지 않습니다.");
        }
    }

}
