# PunkApp

### Used dependencies

 - Retrofit - for networking and getting data from Punk API
 - Jetpack Compose - brand new UI toolkit from Google, it's the future of building UI on Android it was fun to use it as showcase
 - ViewModel and LiveData - for communication between data and UI
 - Navigation - navigation framework from Google, allows building visualized graphs and manage navigation destinations, it's an overkill for this app but I wanted to try it out
 - Koin - dependency injection framework for Kotlin, super easy to setup and includes support for ViewModel
 - Coroutines - it might not be visible at first glance but Retrofit support coroutines when functions are marked with `suspend` and I have used LiveData extension that works great as coroutine


### App structure

#### API

I always separate API models and service. Even if some models could be used directly I won't use it. It's much easier to manage and refactor changes in the API. In case of any change, I would just go the converter responible for mapping models to UI models and it's done.

#### Data

BeerRepository is responsible for fetching beers or single beer from the memory.

#### Features

As you can see I haven't created UI package and grouped fragments, view models etc. I think it's better to structure the rest of the app by feature. That way we can easily say what this app is about looking at packages. It's much easier to navigate and if there is more developers working on differente feature, the would not interfere each other.

Features here are: showing list of beers and showing a single beer details page.
Common components for those features are:
- Fragment used as main container with `ComposeView`
- Own ViewModel using shared Repository
- Own Converter responsible for turning API models in UI models. Allow flatten more complicated structures into simplified version that UI might expect and bind easily
- Own UI models which should always be 1-1 with data that is actually required for view

#### Main

Since this is a single Activity app, I have left MainActivity and Application classes in main package to point that fact and initial to look at.

### App Design

I am not an app designer nor beer expert :smile: . So I wasn't 100% sure how the data should be presented. So I just make sure, data gets on the screen.