# Qual é o episódio do Nerdcast?

Você que é fã do Nerdcast, o podcast do Jovem Nerd (Alexandre Ottoni) e Azaghal (Deive Pazos) e as vezes quer lembrar em qual episódio disseram alguma palavra ou frase?

ENTÃO SEUS PROBLEMAS ACABARAM! 

Junto com a colaboração da forte comunidade do Jovem Nerd no subreddit, desenvolvi um projeto que utiliza um dataset com a transcrição de todas as falas dos **episódios 001 até 921**.

A aplicação é feita utilizando Java Springboot com a biblioteca openCSV para ler o dataset e a biblioteca gson para converter os dados do CSV para JSON para facilitar a busca.

**Créditos:** Transcrição feita pelo usuário "u/Leonardo120602" do subreddit "r/jovem".

[Link do post da transcrição no subreddit](https://www.reddit.com/r/jovemnerd/comments/1bpgz0w/transcri%C3%A7%C3%A3o_dos_nerdcasts_do_001_ao_921/ "Link do post no sub")

[Link do kaggle com o dataset dos episódios 001 a 921](https://www.kaggle.com/datasets/leonardocosta1206/nerdcast-transcriptions "Link do kaggle com o dataset dos episódios 001 a 921")

## Tecnologias
- Java 17 - Linguagem de programação
- Spring Boot - Framework
- OpenCSV - Biblioteca
- Gson - Biblioteca

# Instruções
- Necessário baixar o dataset no kaggle e renomear o arquivo para “nc-dataset.csv”;
> Não consigo colocar o arquivo junto do projeto, pois o github tem um limitador de até 100MB por arquivo.
- Colocar o arquivo "nc-dataset.csv" na “resources” desse projeto;
- Criar uma pasta chamada “NC” na unidade “C:\” do seu computador;
- Rodar o “GerarJsonNC.java” para gerar o arquivo “json-nc.txt”;
- Colocar o arquivo “json-nc.txt” na “resources” desse projeto;
- Após seguir todos os passos acima, utilizar o “QualEpisodioNC.java” para realizar as buscas no dataset (001-921);
> Exemplo de retorno: Encontrado em: NerdCast 281 | 00:37:15
