# ws-pessoa ![badge-jdk-8] ![badge-tool-maven] ![badge-junit-jupiter] ![badge-size] [![Build Status](https://travis-ci.org/kbmg28/ws-pessoa.svg?branch=master)](https://travis-ci.org/kbmg28/ws-pessoa) [![CircleCI](https://circleci.com/gh/kbmg28/ws-pessoa.svg?style=svg)](https://circleci.com/gh/kbmg28/ws-pessoa) [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

Micro serviço no padrão Model-View-Controller (MVC). Desenvolvido utilizando a abordagem do TDD, Integração Contínua e Deploy em nuvem.

## Pré-requisitos para uso local

1. [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Obrigatório. 
2. [Maven](http://maven.apache.org/download.cgi) - Dúvidas para windows consulte esse [tutorial](http://luizricardo.org/2014/06/instalando-configurando-e-usando-o-maven-para-gerenciar-suas-dependencias-e-seus-projetos-java/).
3. [Spring](https://spring.io/tools) - IDE desenvolvida, opcional.

## Iniciar via spring IDE: Variáveis de ambiente
`PROFILE = test` para uso do h2 (Banco em memória).

## Iniciar via terminal ![badge-tool-console]
Navegue até onde o diretório foi extraído e na raíz do projeto e execute os comandos:
1. `mvn clean install -DPROFILE=test`
2. `mvn spring-boot:run -Dspring-boot.run.profiles=test`


### Acessar API local
http://localhost:8000/ws-pessoa/swagger-ui.html#

### Acessar API online
Essa operação deve demorar alguns segundos no primeiro acesso. 

https://kbmg28-java.herokuapp.com/ws-pessoa/swagger-ui.html


[badge-jdk-8]: https://img.shields.io/badge/jdk-8-lightgray.svg "JDK-8"
[badge-tool-maven]: https://img.shields.io/badge/tool-maven-0440af.svg "Maven wrapper included"
[badge-tool-console]: https://img.shields.io/badge/tool-console-022077.svg "Command line tools"
[badge-junit-jupiter]: https://img.shields.io/badge/junit-jupiter-green.svg "JUnit Jupiter Engine"
[badge-size]: https://img.shields.io/github/repo-size/kbmg28/ws-pessoa?style=flat-square
