# sportbuff
Sport Buff Android Application (Proff of Concept)

# The Task:

Create an Android Application using our Rest API to show a list of available Streams.

When you tap on the stream you should show a video player that plays the stream video.



## Android Application

We have provided you with a sample skeleton Android Project, which includes an Android Application that consumes the SDK that you will create as part of your Tech Task and plays a video file.

The Buffs that your SDK create should display over this Video File.



## Android SDK

We want an SDK that the Provided Android App Uses to display content (Buff's as we call them) over the existing video.

The SDK should expose a view that the host Application is adding over the video frame that displays the Buff content there.



### SDK Requirements

The SDK should have the following features

- Expose a view that the host App will add in the UI over the video stream to display the Buffs
- Handle all the business and UI logic to display the Buffs over the video in the view



### Buff UI

The Buff UI should look like this:

![Buff](Buff.png)



The UI has 3 sections:

- Top Section that displays the Questions Sender Name and Image
- Middle section where we see the question and the countdown timer
- Bottom Section where we see the answers

- [ ] The countdown timer should work and at the end if the user hasn't yet voted, the question should automatically hide

- [ ] The number of answers can vary from 2 to 5, your UI should automatically adapt to the number of answers that the API delivers

- [ ] If the user selects an answer, the timer should stop and you should hide the Buff after 2 seconds. You should also highlight the selected answer.


***The UI for this screen with the downloadable assets can be found here:***

https://xd.adobe.com/view/763a1597-da0c-4b42-6fb9-73d5666aef52-000b/

#### The Sample Skeleton App contains XML files for the parts needed to create the above UI as well as assets.

It's up to you to decide to use those XML files, modify them or create your owns as part of the UI layout, however they are provided so you can spend time on the business logic and not on the UI it self.

##### A sample video of the Buffs showing on the current iOS version can be seen here:

https://github.com/buffup/AndroidTechTest/blob/master/Buff.mov?raw=true

## What we are looking for:

- An android application written in Kotlin and an accompanying SDK writen in Kotlin 
- Demonstration of coding style and design patterns.
- Knowledge of common android libraries and any others that you find useful.
- Error handling.
- Any form of unit or integration testing you see fit.
- The application must run on Android 5.0 and above.
- The application must compile and run in Android Studio.

## How to Submit your solution:

- Clone this repository
- Create a public repo in github, bitbucket or a suitable alternative and provide a link to the repository.
- Provide a readme in markdown which details your code and any libraries that you may have used.

## API Usage

This a brief summary of the api endpoints you will need in order to create the App and the SDK. There a lot of other additional properties from the json responses that are not relevant, but you must use these endpoints to retrieve the information needed for this task.

#### Base URL

The base URL for the staging environment is `http://demo2373134.mockable.io/`. 

#### Get  Buff

Gets the data for the Buff to show

```
GET /buffs/:buffId

Buff Id is the id of the buff to fetch
In the sample Rest API Buffs with Id's 1 to 5 are available
```

Sample response:

```
{
    "result": {
        "id": 155,
        "client_id": 1,
        "stream_id": 1,
        "time_to_show": 10,
        "priority": 3,
        "created_at": "2020-01-31T22:19:43.180391Z",
        "author": {
            "first_name": "Pedro",
            "last_name": "Luz"
        },
        "question": {
            "id": 155,
            "title": "Ball Circle Personal Loan Account impactful",
            "category": 1
        },
        "answers": [
            {
                "id": 387,
                "buff_id": 0,
                "title": "324324"
            },
            {
                "id": 388,
                "buff_id": 0,
                "title": "wqewqewq"
            }
        ],
        "language": "en"
    }
}
```

Using the above URL's to fetch the various Buffs, request the Buffs every 30 seconds (from 1 to 5) and display them over the video stream.

The Buff should be displayed with a countdown timer matching the time in the `time_to_show` field of each Buff.
If the user votes before the end of the timer (taps on an answer), you should freeze the timer and hide the Buff after 2 seconds.

If the timer expires and the user doesn't vote, you should hide the Buff.

If the user manually closes the Buff by tapping on the top right `x` close button, you should hide the Buff.

### Time of completion: 5 hours

### Tools used:

    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    //Gson
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    //ViewModel
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    //LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    //OkHttp3 Logging interceptor
    implementation "com.squareup.okhttp3:logging-interceptor:4.4.0"
    //Constraint Layout
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    //RecyclerView
    implementation "androidx.recyclerview:recyclerview:1.1.0"
    //Glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'

