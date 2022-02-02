# ![App Icon](app_icon.png) Payoneer Exercise App

## Written by Brian Robles

An app that downloads JSON data containing a list of applicable networks
from the assigned URL, and can then display a second screen with a
list of inputs generated from a chosen applicable network.

-----

### Architecture Choices
- Java, GSON, Lombok, ViewBinding, Google Libraries.
- No other third-party libraries per assignment requirements
- Multi-Activity Architecture
- Manual dependency injection using an app-specific container pattern
- Manual network calls using HttpURLConnection, Executors, Handlers
- ViewModel and LiveData for MVVM
