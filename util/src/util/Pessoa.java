package util;

public class Pessoa extends Alterable {

	public enum Sexo {
		M("MASCULINO"), F("FEMIMINO");
		
		private String desc;
		
		Sexo(String desc) {
			this.desc = desc;
		}
		 
		public String toString() {
			return desc;
		}
	}
	
	@BeforeGet(method="beforeGet")
	@AfterSet(method="afterSet")
	private String nome;
	private int idade;
	private Sexo sexo;
	
	public void afterSet() {
		System.out.println("executa após setar o nome");
	}
	
	public void beforeGet() {
		System.out.println("executa antes de dar get no nome");
	}
	
	public Pessoa() {
	}
	
	public static void main(String[] args) throws Exception {
		Pessoa p = new Pessoa();
		
		// Get e Set com acesso total, @Inacessible não usado.
		p.set("sexo", Sexo.M);
		System.out.println(p.get("sexo"));
		
		p.set("nome", "Renan");
		//nesse caso gera exceção get = true na @Inacessible
		//descomente para ver
		//System.out.println(p.get("nome")); 

		//nesse caso gera uma exceção set = true na @Inacessible
		//descomente para ver
		//p.set("idade", 21); 
		System.out.println(p.get("nome"));
	}
	
	
}
