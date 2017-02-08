import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main a = new Main();
		a.initialize();
	}

	public void initialize() {
		String current_dir = getStartDirectory();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to KirbyShell (>'.')>");
		while (true) {
			System.out.print("(>'.')>");
			String input = scanner.next().toLowerCase();
			switch (input) {
			// Lists content of current directory
			case "l":
			case "list":
				getContent(current_dir);
				break;
			// Gets current directory
			case "w":
			case "wai":
				System.out.println(current_dir);
				break;
			case "h":
			case "help":
				getHelp();
				break;
			// Exits Shell
			case "e":
			case "exit":
				System.exit(0);
				break;
			case "u":
			case "up":
				current_dir = moveUp(current_dir);
				break;
			case "d":
			case "down":
				input = scanner.nextLine().substring(1);
				current_dir = moveDown(input, current_dir);
				break;
			case "cracker":

				break;
			default:
				System.out.println("invalid command type help to get list of valid commands");
			}
		}
	}
	//takes working directory and the desired directory and appends one to the other
	public String moveDown(String directory, String current_directory) {
		String new_path = current_directory + directory;
		//checks for valid path
		File file = new File(new_path);
		//if it's not a valid path return last working directory
		if (!file.isDirectory()) {
			System.out.println("invalid directory");
			return current_directory;
		}
		//if path does exist return the new_path
		if (file.exists()) {
			System.out.println(new_path);
			return new_path;
		}
		return null;

	}
	//moves working directory up one level
	public String moveUp(String current_directory) {
		String path[] = current_directory.split("\\\\");
		String new_path = "";
		for (int i = 0; i < path.length - 1; i++) {
			new_path += path[i] + "\\";
		}
		File file = new File(new_path);
		if (!file.isDirectory()) {
			System.out.println("invalid directory");
			return current_directory;
		}
		//if path does exist return the new_path
		if (file.exists()) {
			System.out.println(new_path);
			return new_path;
		}
		return null;
	}

	// uses dirtext and parses it looking for just the directory line
	public String getStartDirectory() {
		return System.getProperty("user.dir");
	}

	// uses dirtext and parses it only printing the content in the directory
	public void getContent(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		// System.out.println(folder.length());
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("(f) " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("(d) " + listOfFiles[i].getName());
			}
		}
	}

	// Prints the commands for kirbyshell
	public void getHelp() {
		String[] cmd = { " \t(L)ist: lists contents of current directory",
				"\t(d)own [dir]:moves into the specified child directory", "\t(u)p: moves to the parent directory",
				"\t(w)ai: prints the current directory", "\t(e)xit: exits the shell",
				"\t(h)elp: prints a list of the supported commands" };
		for (int i = 0; i < cmd.length; i++) {
			System.out.println(cmd[i]);
		}
	}

}