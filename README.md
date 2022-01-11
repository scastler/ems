# EMS

### How I proceed

I use to solve android screens from UI layer to repository, but in this case, I hadn't got the design. For this reason, I started to develop from the repository and go on to the UI.

#### Repository
The data is provided from Gitlab ems-demo project and is getted with Retrofit. I choose Retrofit because is a type-safe HTTP client that is pluggable with the top Call Adapters (in my case, Coroutines).

#### Business Logic
This layer contains the Use Cases and entities

#### Presentation
To present the view, I have used ViewModel to get all the advantatges that provide with the Android lifecycle. In this way, I had not got the necesity to manage making easier to test and harder to crash.
The ViewModel contain the state of the view that is observed from the Activity to react with the changes.
To draw the view, I have used the new sdk Jetpack Compose.
With simple functions I could draw and reuse a lot of code making easier to represent the data.

#### Architecture
The architecture used is MVVM by Google

### Next Steps
The next steps in this project is to add a local storage for the historic data. This data will be updated each time the app is started and in this way, the statistic will always be updated.
The Screen needs a better design.
Add a tracker to get all the user interactions
