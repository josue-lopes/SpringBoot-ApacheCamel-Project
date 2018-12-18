package org.apache.camel.spring;

public class SpringBootMain {

	public static void main(String[] args) throws Exception
	{
		Main main = new Main();
		main.setApplicationContextUri("CamelRoute.xml");
		main.run();
		main.wait(500, 0);
	}

}
