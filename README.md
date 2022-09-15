This project complete tasks:
- Using the popular movie db API, you have to create an app which has 2 screens
  (Popular movies screen, Movie detail screen)
- Popular movies screen. Using the get popular movies API, we want you to display
  the list of movies in a list or grid.
- When clicking on a movie item, we should show the movie detail screen using the get
  movie details API

1. How to run project
   git clone the project and syn and build the project 

2. Project structure, components and functions overview 
    1) using Kotlin MVVM, the structure:
       <img src="https://github.com/lypk21/android-movie/blob/master/image1.jpeg" width="600">
    2) using Hint(dagger2) to manage the dependence injection, please refer to di folder
    3) using Room for data cache, when the network disconnected, can still show data to end users.
    4) using Retrofit for network request and Coroutine to manage the network response
    5) using recycleview and smartRefreshLayout to implement pagination function
    6) using Glide for image url cache

3. How the UI look like: 

   <img src="https://github.com/lypk21/android-movie/blob/master/image2.png" width="600">
   <img src="https://github.com/lypk21/android-movie/blob/master/image3.png" width="600">
