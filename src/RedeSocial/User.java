package RedeSocial;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	public String name;
	public String login;
	public String password;
	public int countPosts = 0;
	public Post posts[] = new Post[100];
	public Scanner sc = new Scanner(System.in);
	
	public void menu() {
		System.out.println("\n========================================");
		System.out.println("Bem vindo " + this.name + "!");
		System.out.println("========================================");
		System.out.println("|                                      |");
		System.out.println("|         Digite P para postar         |");
		System.out.println("|  Digite T para acessar sua timeline  |");
		System.out.println("|          Digite S para sair          |");
		System.out.println("|                                      |");
		System.out.println("========================================\n");
		String option = sc.nextLine();
		
		if (option.equals("P")) {
			post();
		} else if (option.equals("T")) {
			timeline();
		} else if (option.equals("S")) {
			RedeSocial.main(null);
		}
	}
	
	public void register(String name, String login, String password) throws InvalidFormatPasswordException {
		passwordValidator(password);
		this.name = name;
		this.login = login;
		this.password = password;
	}
	
	public void passwordValidator(String password) throws InvalidFormatPasswordException{
	    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
	    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        
        if(!matcher.matches()) {
        	throw new InvalidFormatPasswordException();
        }
	}
	
	public void post() {
		
		System.out.println("\nDigite o texto do seu post: ");
		String text = sc.nextLine();
		
		System.out.println("\nDigite a data do seu post: (xx/xx/xxxx)");
		String date = sc.nextLine();

		System.out.println("\nDigite a hora do seu post: (xx:xx)");
		String hours = sc.nextLine();
		
		Post post = new Post();
		
		try {
			post.create(text, date, hours);	
		} catch (InvalidDateException e) {
			System.out.println("\nData inválida! A data deve seguir o formato: 10/02/2020");
			menu();
		} catch (InvalidHoursException e) {
			System.out.println("\nHora inválida! A hora deve seguir o formato: 10:54");
			menu();
		}

		this.posts[this.countPosts++] = post;
		menu();
	}
	
	public void timeline() {
		for(int i = 0; i < this.countPosts; i++) {
			System.out.printf("\n%s às %s - %s\n", posts[i].date, posts[i].hours, posts[i].text);
		}
		menu();
	}
}
