# What is this repository for?

This repository demonstrates a real-world implementation of user position tracking with real-time
updates, following the latest best practices in Android development.

# How do I run the project

In order to run the project:

1. Clone the project into your computer
2. Set the gradle JDK to JAVA 17
3. Sync project gradle files. The minimum current supported version is: 2024.1.1 Patch 1 - Koala
4. Run the app module

# Quality and excellence in code

To maintain code quality and excellence, this repository uses `detekt` to enforce coding standards
across the project.

1. Download
   the [pre-commit](https://drive.google.com/file/d/1ZM5374WPGSeCM8mYaNjf9MGLmdWchCVu/view?usp=sharing)
   file and place it in the `root/.git/hooks` directory.
2. Run `bash .git/hooks/pre-commit` to execute it for the first time and set it up for commit
   checks.

Additionally:
You can run `./gradlew detekt` to perform the checks before create a pull request.

# Dependencies

The project uses [gradle versions catalog](https://docs.gradle.org/current/userguide/platforms.html)
to manage the dependencies and versions.

The definition of dependencies can be find at `gradle/libs.versions.toml`, and new dependencies
should always be added in the file

# Network Monitoring

This project is configured to monitor network requests and responses using a proxy.
The configuration is only available in the `debug` build variant.
Attempting to use a proxy with the `release` build variant will not work due to security
restrictions.

# User Cases

This project includes four main flows: splash, login, maps, and user details, each with specific use
cases.

At the start of the application, a verification of location and notification permissions will be
performed if necessary.

Simultaneously, if permissions are granted, the user's location will start being retrieved. With
each update, the user's position will be sent to an external server, and the user will be notified.

The sending of updates will continue even when the app is sent to the background or closed.

## Considerations

If the user's position cannot be sent, the user will be notified. Once the updates can be sent
successfully, the user will be notified.

## Splash

This flow checks for the existence of a saved user.

### Validations

1. If a user exists, the app will navigate to the maps screen.
2. If no user is found, the app will navigate to the login screen.

## Login

This flow includes user identification functionality based on an email and password.

### Validations

1. The email will be validated to ensure it contains a valid web domain.
2. The password will be validated to ensure it contains at least 8 characters, one special
   character, one uppercase letter, and one number.
3. The login button will only be enabled if the previous validations are met and if there is an
   active internet connection.

The user is fictional and will be stored locally along with the entered email.

### Special Events

1. If the user opens the app without an internet connection, a snackbar will be shown with an alert
   and the option to open settings.
2. If the user loses connection during the data entry process, a snackbar will be shown with an
   alert and the option to open settings.

## Maps

This flow displays the map with the user's moving location path (fictitious route) drawn on it.

It includes two buttons: one for the profile and one for alerts.

1. The profile button will navigate to the user's data screen.
2. The alert button will send the user's data to a remote server with contact information to request
   assistance. The user will be notified via a snackbar whether the alert was sent successfully or
   if there was an error.

### Special Events

1. If the network connection is lost, the map will be hidden, and a screen will be shown indicating
   the loss of connection, with a button to open settings.
2. If the location permission is revoked or not granted initially, a screen will be shown indicating
   the missing permission, with a button to open settings.

## User Details

This flow will display the user's information and will only include the option to return to the maps
screen.
