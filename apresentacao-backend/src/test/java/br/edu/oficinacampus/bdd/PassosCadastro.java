package br.edu.oficinacampus.bdd;
import br.edu.oficinacampus.CampusApplication;
import br.edu.oficinacampus.apresentacao.metadados.CatalogoFormularios;
import br.edu.oficinacampus.apresentacao.metadados.Formulario;
import io.cucumber.java.pt.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@CucumberContextConfiguration
@SpringBootTest(classes=CampusApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,properties={"spring.datasource.url=jdbc:h2:mem:bdd;DB_CLOSE_DELAY=-1","spring.jpa.hibernate.ddl-auto=create-drop"})
public class PassosCadastro {
 @Autowired TestRestTemplate http;
 @Autowired CatalogoFormularios catalogo;
 private Formulario modulo;
 private Map<String,Object> dados,registro,antes;
 private ResponseEntity<Map> resposta;
 private long quantidade;
 @Dado("o cadastro de {string} com dados válidos")
 public void preparar(String nome){modulo=catalogo.obter(nome);dados=new LinkedHashMap<>(modulo.seeds().get(0));registro=null;antes=null;}
 private String url(){return "/api/registros/"+modulo.id();}
 @Quando("crio a ficha") public void criar(){resposta=http.postForEntity(url(),Map.of("dados",dados),Map.class);if(resposta.getStatusCode().is2xxSuccessful())registro=new LinkedHashMap<>(resposta.getBody());}
 @Entao("a resposta deve ter código {int}") public void codigo(int status){assertEquals(status,resposta.getStatusCode().value(),String.valueOf(resposta.getBody()));}
 @Entao("o estado deve ser {string}") public void estado(String esperado){assertEquals(esperado,registro.get("estado"));}
 @Entao("a ficha deve continuar disponível para consulta") public void consultar(){var consultada=http.getForEntity(url()+"/"+registro.get("id"),Map.class);assertEquals(200,consultada.getStatusCode().value());assertEquals(registro,consultada.getBody());}
 @Quando("altero um campo com informação válida") public void alterar(){var f=modulo.fields().get(0);dados.put(f.key(),f.kind().equals("number")?3:"Cadastro revisado para apresentação");resposta=http.exchange(url()+"/"+registro.get("id"),HttpMethod.PUT,new HttpEntity<>(Map.of("dados",dados,"versao",registro.get("versao"))),Map.class);if(resposta.getStatusCode().is2xxSuccessful())registro=new LinkedHashMap<>(resposta.getBody());}
 @Quando("solicito {string}") public void acao(String acao){resposta=http.postForEntity(url()+"/"+registro.get("id")+"/"+acao,Map.of("versao",registro.get("versao")),Map.class);if(resposta.getStatusCode().is2xxSuccessful())registro=new LinkedHashMap<>(resposta.getBody());}
 @Entao("os dados da ficha devem ser preservados") public void dadosPreservados(){assertEquals(dados,registro.get("dados"));}
 @Quando("deixo um campo obrigatório vazio") public void vazio(){var f=modulo.fields().stream().filter(x->x.required()).findFirst().orElseThrow();dados.put(f.key(),null);}
 @Dado("a quantidade de fichas antes da tentativa") public void contar(){quantidade=((Number)http.getForObject(url(),Map.class).get("total")).longValue();}
 @Entao("nenhuma ficha deve ser adicionada") public void semCriacao(){assertEquals(quantidade,((Number)http.getForObject(url(),Map.class).get("total")).longValue());}
 @Dado("uma cópia anterior da versão") public void copiar(){antes=new LinkedHashMap<>(registro);}
 @Quando("tento sobrescrever usando a versão anterior") public void antiga(){resposta=http.exchange(url()+"/"+registro.get("id"),HttpMethod.PUT,new HttpEntity<>(Map.of("dados",modulo.seeds().get(0),"versao",antes.get("versao"))),Map.class);}
 @Entao("a ficha {string} aparecer na consulta pública") public void publico(String deve){var list=http.getForObject("/api/public/conteudo",List.class);boolean existe=list.stream().anyMatch(x->((Map)x).get("id").equals(registro.get("id")));assertEquals(deve.equals("deve"),existe);}
 @Quando("informo os componentes {string}") public void componentes(String tipo){dados.put("componentes",tipo.equals("vazios")?List.of():List.of("Papel","Caneta"));}
 @Quando("informo o campo {string} com {string}") public void campo(String campo,String valor){dados.put(campo,valor);}
}
