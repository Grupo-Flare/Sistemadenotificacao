# Sistemadenotificacao

**DESAFIO IV - ALERTA** - Otimização na comunicação com alunos, professores em caso de ausências e atrasos.

Neste desafio o grupo precisa desenvolver um aplicativo de alertas urgentes. 
Seria um sistema para que a instituição pudesse fazer notificações urgentes e curtas, como por exemplo: 
notificar atraso de professor, notificar evento, notificar reunião, notificar inscrições para
alguma atividade, informações e alertas sobre cuidados com relação à COVID-19.

## Como executar 

```
./mvnw spring-boot:run
```
Para a criação das telas da aplicação não é necessario iniciar todo o projeto java, basta abrir o projeto com o VsCode e usar a extensão live-server. 

## Usando o Git e GitHub
### Baixando o projeto
```
git clone https://github.com/Grupo-Flare/Sistemadenotificacao.git
```

Esta é a branch principal (`main`), é onde vai estar todo o código em desenvolvimento.

Ao desenvolver uma nova funcionalidade crie uma nova branch para poder trabalhar na modificação. Uma boa pratica é nomear a branch com o nome da funcionalidade, ou a funcionalidade junto do seu nome.

Ex.: **busca_alunos**

Crie uma nova branch com o comando:

```
git checkout -b <nome_da_branch>
```

Quando terminar de trabalhar na modificação, suba todo o codigo para o github e **crie um pull request** para a branch main.

Na primeira vez em que subir a nova branch, execute:

```
git push --set-upstream origin <nome_da_branch>
```

Nas proximas vezes podera ser somente:

```
git push
```

### Fazendo download de outras branches

Durante o desenvolvimento é possivel que precisaremos que fazer o download de outras branches para fazer testes ou continuar o desenvolvimento.

O comando para isso é:
```
git checkout -t <nome_branch> origin/<nome_branch>
```

------

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

## Links uteis

[Prototipo no Figma](https://www.figma.com/file/8gRuOJaswo6iokWmzTI1kR/SistemaDeNotifica%C3%A7%C3%A3o?node-id=0%3A1)

[Estrutura e regra de négocio](https://miro.com/app/board/o9J_lrBHrqc=/?invite_link_id=967976314075)

[O que é GIT e GITHUB?](https://www.youtube.com/watch?v=DqTITcMq68k)

[Como usar GIT e GITHUB na prática!](https://www.youtube.com/watch?v=DqTITcMq68k)
