BlockForge

BlockForge é um framework modular para o desenvolvimento de aplicações Java desktop baseadas em plugins.

A arquitetura do BlockForge é composta por um Core responsável pelo gerenciamento da aplicação e por módulos externos distribuídos como arquivos JAR. Os módulos são carregados e gerenciados pelo Core em tempo de execução, permitindo que funcionalidades sejam adicionadas, removidas ou atualizadas sem a necessidade de recompilar o núcleo da aplicação.

Cada módulo funciona como um subframework especializado em uma determinada área do sistema, como:

Gerenciamento e persistência de dados locais;

Segurança;

Conectividade web;

Acesso a recursos do sistema;

Interface gráfica do usuário (GUI).

Os módulos disponibilizam suas próprias APIs para os desenvolvedores, permitindo que aplicações construídas sobre o BlockForge utilizem suas funcionalidades sem precisar conhecer os detalhes internos de suas implementações.

A comunicação e o fornecimento de dependências entre o Core, os módulos e a aplicação são realizados por meio de Dependency Injection (DI). Dessa forma, o acoplamento entre os componentes é controlado pela composição das dependências, permitindo que implementações sejam substituídas ou evoluídas sem que os consumidores precisem conhecer sua instanciação.

A modularização tem como objetivo facilitar a manutenção, evolução e atualização da aplicação, permitindo que módulos sejam distribuídos e atualizados independentemente do Core. Uma alteração em um módulo não exige necessariamente a recompilação ou redistribuição de toda a aplicação.

Este projeto

Este repositório contém o módulo responsável pelo gerenciamento e manipulação de bancos de dados locais para aplicações baseadas no BlockForge.

Atualmente, o módulo utiliza:

SQLite

ORMLite

A arquitetura do módulo está sendo desenvolvida para permitir o suporte a diferentes frameworks de persistência, inicialmente com ORMLite e, futuramente, EclipseLink.

Status

🚧 Em desenvolvimento
