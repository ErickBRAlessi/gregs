package br.ufpr.tcc.gregs.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.ufpr.tcc.gregs.models.Componente;
import br.ufpr.tcc.gregs.models.ComponenteFlickr;
import br.ufpr.tcc.gregs.models.ComponenteImagem;
import br.ufpr.tcc.gregs.models.ComponenteTexto;
import br.ufpr.tcc.gregs.models.Imagem;
import br.ufpr.tcc.gregs.models.Pagina;
import br.ufpr.tcc.gregs.models.Pessoa;
import br.ufpr.tcc.gregs.models.Texto;
import br.ufpr.tcc.gregs.models.Usuario;
import br.ufpr.tcc.gregs.service.IPaginaService;
import br.ufpr.tcc.gregs.service.IPessoaService;
import br.ufpr.tcc.gregs.service.IUsuarioService;

@Component
public class DataGenerator {
	
	@Value("${img64}")
	private String base64;
	
	@Autowired
	private IPessoaService iPessoaService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IPaginaService iPaginaService;
	
	public void popular() {
		try {

			for (int i =0 ; i<=20 ; i++){
				ComponenteImagem cImg = new ComponenteImagem();
				cImg.setBackgroundColor("#F4F4F4");
				cImg.setForegroundColor("#F0F0F0");
				cImg.setMostrarTitulo(true);
				cImg.setTitulo("Um Projeto Aí");
				cImg.setImagem(new Imagem("imagem do projeto",
						null,
						null));//arrumar essa img
				cImg.setDescricao("Minha Imagem");

				ComponenteTexto cTxt = new ComponenteTexto();
				cTxt.setBackgroundColor("#F4F4F4");
				cTxt.setForegroundColor("#F0F0F0");
				cTxt.setMostrarTitulo(true);
				cTxt.setTitulo("Um Projeto Aí");
				List<Texto> textos = new ArrayList<>();
				textos.add(new Texto("É o Título do Meu Texto", "São Textos da Vida"));
				textos.add(new Texto("Sempre Tem que Testar Mais de Um", "Os textos são loucos"));
				cTxt.setTextos(textos);

				List<Componente> componentes = new ArrayList<>();
				componentes.add(cImg);
				componentes.add(cTxt);

				Pessoa pessoaProgramador = new Pessoa("João numero" + i, "Desenvolvedor");
				Usuario usuarioProgramador = new Usuario("joao" + i +"@programador.com", "asdasd", pessoaProgramador);
				usuarioProgramador.setPagina(new Pagina("joaoprogramador"+i, componentes));
				usuarioProgramador.setImagemUsuario(new Imagem("imagem do projeto", null, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Bloemkool.jpg/800px-Bloemkool.jpg"));

				iUsuarioService.salvar(usuarioProgramador);


				MotorBusca.inserirTagsUsuario(generateTags(), usuarioProgramador);

				List<Pagina> p = iPaginaService.findAll() ;
//				for(Pagina x : p) {
//					System.out.println(x);
//				}

//				System.out.println(iPaginaService.findByUrl("joaoprogramador"));
//				Usuario u = iUsuarioService.findByEmail("joao@programador.com");
//				System.out.println(u);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}

 	private List<String> generateTags(){
		List<String> tags = new ArrayList<>();
		List<String> tagsRetorno = new ArrayList<>();
		tags.add("Desenvolvimento Web");
		tags.add("Programador");
		tags.add("Java");
		tags.add("Front-end");
		tags.add("Designer");
		tags.add("Fotografo");
		tags.add("Motorista");
		tags.add("Preto e branco");
		tags.add("Javinha");
		tags.add("Javascript");
		tags.add("c#");
		tags.add("c++");
		tags.add("c");
		tags.add("pascal");


		for (String tag : tags){
			String tagAux = tags.get(getRandomNumber(0,tags.size()));

			if (!tagsRetorno.contains(tagAux)) tagsRetorno.add(tagAux);
		}


		return tagsRetorno;
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}


}
