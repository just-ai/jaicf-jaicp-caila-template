# JAICF Caila Bot template

Here is a ready to use JAICF bot template that can be run locally or deployed to Heroku server with Caila NLU provider and JAICP channels.

## How to use

You can use this template locally on your PC or deploy it on Heroku server.

### Run locally

To run this project locally: 
1. Create a new project from source codes in IntelliJ IDEA
2. Paste your API Token to `src/main/conf/jaicp.properties`
2. Run either `PollingConnection.kt`, or `WebhookConnection.kt`. Using PollingConnection does not require exposed HTTP URL.

#### Obtain global URL for WebhookConnection  

You can then propagate your local instance to the Internet using [ngrok](https://ngrok.com/).
And then run `ngrok http 8080` in the terminal to obtain public URL.

Copy this URL then and set it in JAICP Web Interface 

### Channels
You can create channels in JAICP Web Interface. Select channels tab inside your project, create a new one, 
let's say, Chat Widget, and then restart your connection (Webhook or Polling) to register changes in channels.
From now on you will be able to communicate with your bot through widget.

Same applies to Telegram, Dialogflow, Facebook, and other channels.

### Deploy to Heroku

Just click on the button below to deploy this template on Heroku server.

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

1. After deployment open the app and copy the URL of the app to clipboard.
2. Then go to JAICP Web Interface, open your project settings, select webhook type of connection and paste your URL.
3. Configure all channels you want to use and restart your application in Heroku.
4. That's it! Now all the channels will communicate through your bot.

### Configuring CAILA in Heroku
There are two ways how to set JAICP Access Token to heroku:
1. Set as environmental variable `JAICP_ACCESS_TOKEN` in web dashboard, as shown [here](https://devcenter.heroku.com/articles/config-vars#managing-config-vars).
2. Set property in `src/main/conf/jaicp.properties`, and upload changes to heroku.
After token was set, you need to restart application in Heroku.


### How to upload code changes to Heroku

Please make the next steps to upload your changes on Heroku.

Install [git](https://git-scm.com/downloads) and [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install).
Run a terminal (or console) on your machine and type

```
heroku login
heroku git:clone -a <your Heroku application name>
cd <your Heroku application name>
git remote add origin https://github.com/just-ai/jaicf-jaicp-caila-template
git pull origin master
```

_You have to do these steps only once._

Once you are ready to upload your changes to Heroku, just type

```
git add .
git commit -am "some comments"
git push
```

Heroku will build and deploy your changes automatically.

### Data persistence

This template uses mongoDB persistence layer available on Heroku.
Heroku automatically creates a free sandbox database hosted by MongoLab.

During the local development a simple in-memory storage is used.