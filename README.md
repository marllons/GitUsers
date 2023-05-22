# GitUsers

O aplicativo “GitUsers” é uma lista de usuários do github, onde você pode listar usuários para ter acesso ao repositório público do mesmo.

O aplicativo consume a API de do github ([http://www.omdbapi.com/](https://api.github.com )) e foi desenvolvido seguindo os princípios do MVVM + Clean Architecture, separando em camadas de `Data`, `Domain` e `Presenter`, sendo este ultimo dividido entre `View e ViewModel`.

- A camada de `Data` utiliza as bibliotecas Retrofit, para consumir a API externa.

- A camada de `Domain` foi implementado os `UseCases` com as regras que acessam a camada `Data` em busca dos dados esternos. Realizando as tarefas de consulta de dados utilizando Coroutines, na thread IO e retornar os dados para thread MAIN com mapeamento dos tipos de erros.

- A camada de `Presenter` é responsável por toda a parte visual do aplicativo, como Activities, Fragments, ViewModels e etc. Foi implementado seguindo os padrões de programação reativa (LiveData e ViewState).
Também utilizando algumas bibliotecas como:
  - ViewBinding: Para o binding direto dos modelos de dados com as interfaces visuais.
  - Glide: Carregamento de imagens.
  - Koin: Injeção de dependências.
  - Material: Componentes de UI do Android.
  
O aplicativo foi desenvolvido utilizando a linguagem Kotlin e toda as comunições entre as camadas são feitas através de Coroutines.


## Como utilizar
Abra o app e pesquise por algum usuário. Ao achar, clique no usuário e veja seus detalhes, bem como seus repositórios.

## Imagens

Default theme <br>
![image](https://github.com/marllons/GitUsers/assets/30272949/9b5eec85-8098-4633-9430-22025cab4d72)
![image](https://github.com/marllons/GitUsers/assets/30272949/e74ff425-7901-4171-8f51-35e13c1e8ce1)
![image](https://github.com/marllons/GitUsers/assets/30272949/02e9c332-1063-4953-a9aa-8e17e8f392ab)
![image](https://github.com/marllons/GitUsers/assets/30272949/6ae5a743-b1e6-4c67-b63f-51282db9ee6e)
![Uploading image.png…]()





<img src="(https://github.com/marllons/GitUsers/assets/30272949/9b5eec85-8098-4633-9430-22025cab4d72" width="250">
<img src="images/02.png" width="250">
<img src="images/03.png" width="250">
