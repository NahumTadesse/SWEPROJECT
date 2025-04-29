# Installation Instructions!
## 1. Check if Git is Installed

```bash
git --version
```

If Git is not installed, install it using Homebrew:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
brew install git
```

## 2. Clone the Repository

Replace `username` and `repo` with the actual GitHub username and repository name:

```bash
git clone https://github.com/NahumTadesse/SWEPROJECT
```

## 3. Setup the Repository
```bash
git stash --include-untracked
git checkout master
```
## 4. Done! Relax, the hard part has been completed.
Now you load up the code in IntelliJ. Make sure that Java 22 is configured or code will not run, it should pop up on the top right hand corner.
If for whatecer reason Maven is giving you trouble you can try

```mvn clean install```
