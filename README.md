BlockForge

BlockForge é um framework modular para o desenvolvimento de aplicações Java desktop baseadas em plugins.

Uma aplicação desenvolvida com o BlockForge é composta por um Core, módulos e plugins. O Core e os módulos fornecem a infraestrutura e os recursos básicos da aplicação, enquanto os plugins são responsáveis por implementar as funcionalidades que compõem o produto final.

A estrutura de uma aplicação BlockForge pode ser representada da seguinte forma:

Aplicação
│
├── Core
│
├── Módulos
│   ├── Banco de dados
│   ├── Segurança
│   ├── Conectividade web
│   ├── Recursos do sistema
│   └── Interface gráfica
│
└── Plugins
    ├── Plugins de domínio
    └── Plugins de terceiros

Arquitetura

O Core é responsável pelo gerenciamento da aplicação e pelo carregamento e gerenciamento dos componentes disponíveis.

Os módulos são componentes externos distribuídos como arquivos JAR. Cada módulo fornece uma API própria e abstrai uma funcionalidade específica da infraestrutura da aplicação. Módulos podem ser desenvolvidos e distribuídos independentemente do Core.

Os plugins são o principal ponto de extensão do BlockForge. Eles utilizam as APIs disponibilizadas pelo Core e pelos módulos para implementar as funcionalidades da aplicação.

Entre os plugins, estão os plugins de domínio, que representam as funcionalidades específicas do sistema que está sendo desenvolvido. Também podem existir plugins desenvolvidos por terceiros, permitindo a extensão da aplicação sem a necessidade de modificar o Core ou os módulos existentes.

As dependências entre os componentes são gerenciadas por meio de Dependency Injection (DI). O objetivo é reduzir o acoplamento entre os componentes e permitir que suas implementações sejam substituídas ou evoluam de forma independente.

Composição da aplicação

Uma aplicação BlockForge pode ser entendida como a composição de quatro camadas:

┌─────────────────────────────────────┐
│          Plugins de domínio         │
│       Funcionalidades da aplicação  │
├─────────────────────────────────────┤
│        Plugins de terceiros         │
│          Extensões adicionais       │
├─────────────────────────────────────┤
│               Módulos               │
│       Infraestrutura da aplicação   │
├─────────────────────────────────────┤
│                Core                 │
│      Runtime e gerenciamento        │
└─────────────────────────────────────┘


O desenvolvedor da aplicação normalmente trabalha principalmente na camada de plugins, utilizando os recursos fornecidos pelo Core e pelos módulos já disponíveis.

Essa arquitetura permite que novas funcionalidades sejam adicionadas à aplicação por meio de plugins, sem que seja necessário modificar diretamente o Core ou os módulos existentes.

Este projeto

Este repositório contém um dos módulos do BlockForge: o módulo responsável pelo gerenciamento e manipulação de bancos de dados locais.

O módulo utiliza atualmente:

SQLite

ORMLite

A arquitetura do módulo está sendo desenvolvida para possibilitar o suporte a diferentes frameworks de persistência, inicialmente com ORMLite e, futuramente, EclipseLink.

Status

🚧 Em desenvolvimento
