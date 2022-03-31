# Sistemadenotificacao

**DESAFIO IV - ALERTA** - Otimização na comunicação com alunos, professores em caso de ausências e atrasos.

Neste desafio o grupo precisa desenvolver um aplicativo de alertas urgentes. 
Seria um sistema para que a instituição pudesse fazer notificações urgentes e curtas, como por exemplo: 
notificar atraso de professor, notificar evento, notificar reunião, notificar inscrições para
alguma atividade, informações e alertas sobre cuidados com relação à COVID-19.

## Como executar 

./mvnw spring-boot:run

## Branches

Esta é a branch principal (`main`), é onde vai estar todo o código já testado. Será também a versão que ira para produção.

A branch `backend` sera onde ocorrera todo o desenvolvimento do backend da aplicação, ou seja, regras de negocio e conexão com o banco de dados.

A branch `frontend` sera onde ocorrera o desenvolvimento do frontend da aplicação, ou seja, as paginas HTML, junto com o CSS e JavaScript

### Como trocar de branch
```
git checkout <nome_da_branch>
```
Se for necessario trocar de branch mas ainda não terminou o que estava fazendo, ou seja, não commitou as mudanças de um "stash" para salvar o que estava fazendo.
```
git stash 
```
Ao voltar no que estava fazendo, de um "apply" nas modificações.
```
git stash apply
```
## Estrutura do projeto
```
|- pom.xml -> Onde estão as dependências do projeto 
 |- mvnw -> Binário do gerenciador de dependências maven 
 |- /src/main -> Diretório onde estão os códigos fontes
 	|- /resources -> Diretório onde estão arquivos de configuração e as paginas html
 	 	|- templates -> Onde estão as paginas html
        |- static -> Onde estão os recursos das paginas html, como imagens
 		|- aplication.properties -> Configurações da aplicação, como conexão com o banco
 	|- /java -> Códigos fontes
 	|- test -> Testes automatizados
```
