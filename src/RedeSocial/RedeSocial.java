package RedeSocial;

import java.util.Scanner;

public class RedeSocial {
	public static User users[] = new User[100]; 
	public static Scanner sc = new Scanner(System.in);
	public static int countUsers = 0;
	
	public static void main(String[] args) {
		System.out.println("\n========================================");
		System.out.println("|             Menu Inicial:            |");
		System.out.println("|                                      |");
		System.out.println("|     Digite C para cadastrar-se       |");
		System.out.println("|       Digite L para fazer login      |");
		System.out.println("|    Digite F para fechar o programa   |");
		System.out.println("|                                      |");
		System.out.println("========================================\n");

		String option = sc.next();
		
		if (option.equals("C")) {
			register();
		} else if (option.equals("L")) {
			try {
				login();
			} catch (UserNotFoundException e) {
				System.out.println("\nUsuário nao encontrado!");
				main(null);
			} catch (InvalidPasswordException e) {
				System.out.println("\nSenha incorreta!");
				main(null);
			}
		} else if (option.equals("F")) {
			System.out.println("\nPrograma encerrando...");
			sc.close();
		} else {
			System.out.println("\nComando não identificado!");
			main(null);
		}
		
		System.out.println("Programa encerrado!");
	}
	
	public static void register() {
		sc.nextLine();

		System.out.print("\nDigite o seu nome: ");
		String name = sc.nextLine().trim();
		
		System.out.print("Digite o seu login: ");
		String login = sc.nextLine().trim();

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine().trim();
		
		System.out.print("Confirme sua senha: ");
		String confirmPassword = sc.nextLine().trim();
		
		if (!password.equals(confirmPassword)) {
			System.out.println("\nO campo senha e confirmar senha devem ser iguais!");
			main(null);
		}

		for(int i = 0; i < countUsers; i++) {
			if (users[i].login.equalsIgnoreCase(login)) {
				System.out.println("\nJá existe um usuário cadastrado com este login!");
				main(null);
			}
		}
		
		if (name.length() == 0) {
			System.out.println("\nO campo 'Nome' não pode ser vazio!");
			main(null);
		} else if (login.length() == 0) {
			System.out.println("\nO campo 'Login' não pode ser vazio!");
			main(null);
		} else if (password.length() == 0) {
			System.out.println("\nO campo 'Senha' não pode ser vazio!");
			main(null);
		}

		User user = new User();
		
		try {
			user.register(name, login, password);			
		} catch (InvalidFormatPasswordException e) {
			System.out.println("\nSua senha deve conter entre 8 e 20 caracteres, sendo um númerico, um maiúsculo e um minúsculo!");
			main(null);
		}

		users[countUsers++] = user;
		
		main(null);
	}
	
	public static void login() throws UserNotFoundException, InvalidPasswordException {
		sc.nextLine();

		System.out.print("\nDigite o seu login: ");
		String login = sc.nextLine();
		
		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();
		
		for(int i = 0; i < countUsers; i++) {
			if (users[i].login.equals(login)) {
				if (users[i].password.equals(password)) {
					users[i].menu();
				} else {
					throw new InvalidPasswordException();
				}
			}
		}
		
		throw new UserNotFoundException();
	}

}
 