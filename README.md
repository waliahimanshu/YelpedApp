# YelpedApp

The app uses the [Yelp business API](https://www.yelp.com/developers/documentation/v3/business_search) to get the restaurants data for a given location.

The app has main screen listing list of restuarantas and detail screen. The detail screen provides a different view for landscape mode.


The app is written with MVVM architecutre.
APP has DTO layer which then mapped to Domain layer objects. The VM works with the Domain objects, which then propgated to UI layer
All UI states are persisted across confuguration changes with Android ViewModel.
UI State is propagated from ViewModel to Ui with LiveData.
`SavedStateHandle` is used in the detail scree, so the screen will survive the process death.
The data is cached with Room so it also works when there is no internet connection.
Hilt is used for dependenc injection.
View Models and Repository are unit tested


UI 

