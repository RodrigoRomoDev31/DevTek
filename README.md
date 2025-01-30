### What is this repository for? ###

This repository demonstrates a real-world implementation of user position tracking with real-time
updates, following the latest best practices in Android development.

### How do I run the project ###

In order to run the project:

1. Clone the project into your computer
2. Sync project gradle files. The minimum current supported version is: 2024.1.1 Patch 1 - Koala
3. Run the app module

#### Quality and excellence in code

To maintain code quality and excellence, this repository uses `detekt` to enforce coding standards
across the project.

1. Download
   the [pre-commit](https://drive.google.com/file/d/1ZM5374WPGSeCM8mYaNjf9MGLmdWchCVu/view?usp=sharing)
   file and place it in the `root/.git/hooks` directory.
2. Run `bash .git/hooks/pre-commit` to execute it for the first time and set it up for commit
   checks.

Additionally:
You can run `./gradlew detekt` to perform the checks before create a pull request.

## Dependencies

The project uses [gradle versions catalog](https://docs.gradle.org/current/userguide/platforms.html)
to manage the dependencies and versions.

The definition of dependencies can be find at `gradle/libs.versions.toml`, and new dependencies
should always be added in the file

## Network Monitoring

This project is configured to monitor network requests and responses using a proxy.
The configuration is only available in the `debug` build variant.
Attempting to use a proxy with the `release` build variant will not work due to security
restrictions.
