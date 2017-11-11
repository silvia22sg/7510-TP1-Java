package ar.uba.fi.tdd.rulogic;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws IOException {
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		
		KnowledgeBase kb = new KnowledgeBase("src/main/resources/rules.db");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese una consulta. Salir presionando 'q'.");
		String line = buffer.readLine();
		
		while (!line.equals("q")) {
			System.out.println(kb.answer(line));
			line = buffer.readLine();
			System.out.flush();
			System.setOut(old);
			System.out.println(baos.toString());
		}  
    }
}
