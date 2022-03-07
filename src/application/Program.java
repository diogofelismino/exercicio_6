package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Employee> employee = new ArrayList<>();
		
		System.out.print("Digite o caminho do arquivo: ");
		String path = sc.nextLine();
		
		System.out.print("Entre com o valor do salario: ");
		double slr = sc.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			
			while(line != null) {
				String[] vect = line.split(",");
				employee.add(new Employee(vect[0], vect[1], Double.parseDouble(vect[2])));
				
				line = br.readLine();
			}
			
			Comparator<String> comparator = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> names = employee.stream().filter(p -> p.getSalary() > slr)
								.map(p -> p.getEmail())
								.sorted(comparator)
								.collect(Collectors.toList());
			
			System.out.println("Email da pessoa com o salario até " + String.format("%.2f", slr) + ": ");
			names.forEach(System.out:: println);
			
			double sum = employee.stream().filter(p -> p.getName().toUpperCase().charAt(0) == 'M')
								.map(p -> p.getSalary())
								.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Soma de todos os salarios do funcionario que comiça com a letra 'M' : " + String.format("%.2f", sum));					
								
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			sc.close();
		}

	}

}
