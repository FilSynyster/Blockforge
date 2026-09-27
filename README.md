BlockForge
BlockForge é um framework modular para o desenvolvimento de aplicações Java desktop baseadas em plugins.

Uma aplicação desenvolvida com o BlockForge é composta por um Core, módulos e plugins. O Core e os módulos fornecem a infraestrutura e os recursos básicos da aplicação, enquanto os plugins são responsáveis por implementar as funcionalidades que compõem o produto final.

# Arquitetura

O Core é responsável pelo gerenciamento da aplicação e pelo carregamento e gerenciamento dos componentes disponíveis.
Os módulos são componentes externos distribuídos como arquivos JAR. Cada módulo fornece uma API própria e abstrai uma funcionalidade específica da infraestrutura da aplicação. Módulos podem ser desenvolvidos e distribuídos independentemente do Core.
Os plugins são o principal ponto de extensão do BlockForge. Eles utilizam as APIs disponibilizadas pelo Core e pelos módulos para implementar as funcionalidades da aplicação.
Entre os plugins, estão os plugins de domínio, que representam as funcionalidades específicas do sistema que está sendo desenvolvido. Também podem existir plugins desenvolvidos por terceiros, permitindo a extensão da aplicação sem a necessidade de modificar o Core ou os módulos existentes.
As dependências entre os componentes são gerenciadas por meio de Dependency Injection (DI). O objetivo é reduzir o acoplamento entre os componentes e permitir que suas implementações sejam substituídas ou evoluam de forma independente.

# Composição da aplicação
Uma aplicação BlockForge pode ser entendida como a composição de quatro camadas:

 - Plugins de domínio (Funcionalidades da aplicação)
 - Plugins de terceiros (Extensões adicionais)
 - Módulos (Infraestrutura da aplicação)
 - Core (Runtime e gerenciamento)

O desenvolvedor da aplicação normalmente trabalha principalmente na camada de plugins, utilizando os recursos fornecidos pelo Core e pelos módulos já disponíveis.
Essa arquitetura permite que novas funcionalidades sejam adicionadas à aplicação por meio de plugins, sem que seja necessário modificar diretamente o Core ou os módulos existentes.

# Módulos
O BlockForge permite o desenvolvimento de módulos especializados que podem ser distribuídos como componentes independentes da aplicação.
Alguns exemplos de módulos previstos para o framework:

 - Database (Gerenciamento e persistência de dados locais)
 - Security (Recursos relacionados à segurança)
 - Web (Conectividade e comunicação com serviços web)
 - System (Acesso a recursos do sistema operacional)
 - GUI (Recursos para construção da interface gráfica)

Os módulos disponibilizam suas próprias APIs para os plugins, permitindo que o desenvolvedor utilize seus recursos sem precisar conhecer os detalhes internos de suas implementações.

# Plugins
Os plugins são o principal mecanismo de desenvolvimento e extensão das aplicações BlockForge.
Um plugin pode utilizar os recursos fornecidos pelo Core e pelos módulos para implementar funcionalidades específicas da aplicação.

Os plugins podem ser classificados, principalmente, em:

 - Plugins de domínio (São os plugins desenvolvidos especificamente para a aplicação final)

Eles representam as regras de negócio e funcionalidades próprias do sistema que está sendo construído.

Por exemplo:

Aplicação de gestão:

Core

 - Módulos nativos:

Database

Security

...

 - Plugins de domínio:
 - 
Plugin: Clientes

Plugin: Produtos

Plugin: Vendas

Plugin: Relatórios

Nesse cenário, Clientes, Produtos, Vendas e Relatórios são plugins de domínio da aplicação.

 - Plugins de terceiros (São plugins desenvolvidos por outros desenvolvedores e podem ser adicionados à aplicação para fornecer funcionalidades adicionais)

Isso permite que aplicações BlockForge sejam estendidas sem a necessidade de modificar diretamente o Core, os módulos ou os plugins de domínio existentes.

# Dependency Injection
O BlockForge utiliza Dependency Injection (DI) como mecanismo para composição e gerenciamento das dependências entre seus componentes.
O objetivo é reduzir o acoplamento entre o Core, módulos e plugins, permitindo que as dependências sejam fornecidas pelo ambiente de execução em vez de serem instanciadas diretamente pelos componentes consumidores.
Dessa forma, o desenvolvedor pode trabalhar com as APIs disponibilizadas pelos módulos e pelo Core, enquanto a infraestrutura do BlockForge é responsável pela composição das dependências.

# Este projeto
Este repositório contém um dos módulos do BlockForge: o módulo responsável pelo gerenciamento e manipulação de bancos de dados locais.

Atualmente, o módulo utiliza:

 - SQLite

 - ORMLite

A arquitetura do módulo está sendo desenvolvida para possibilitar o suporte a diferentes frameworks de persistência, inicialmente com ORMLite e, futuramente, EclipseLink.

Status: Em desenvolvimento.
