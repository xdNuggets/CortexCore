# Contributing to CortexCore

Thanks for wanting to contribute! We want to maintain a clean structure so it is easy for anyone, no matter the expertise level, to read and understand.
The plugin will be coded in Java. Despite Kotlin and Java being compatible, having two languages will make it confusing, and most of us know Java.
We also ask that you comment your code where appropriate.
For style, please follow the [Google Style](https://google.github.io/styleguide/javaguide.html).


## Structure

Our plugin structure is as follows:

```
common
└── gui
└── commands
modules
└── module1
└── module2
└── ...
src
└── main
    └── java
        └── dev.cortex.cortexcore
```

It consists of 3 main areas:
- The common directory. This contains utility that is used globally throughout the plugin and its modules. This includes our gui and command frameworks.
- The modules directory. This is where the bulk of the work is done. These are all individual sections dedicated to different features such as essential commands or systems entirely.
- the src directory. This is where the main plugin is. Listeners are registered here, as well as things that aren't used in modules.

# How do I get started?

To get started, fork the repository and create a new branch. Name your branch to what you are adding and what module it is in. For example, if I want to add a /fly command to the common module, I name my branch `common-add-fly-command`. 
When committing to your branch, we ask that you follow the conventional commits [style](https://www.conventionalcommits.org/en/v1.0.0/).

Once your changes are ready you must submit your branch as a pull request. Your pull request should be opened against the `main`.

In your PR's description, you should follow the structure as outlined in the PR template:

1. Description: Describe your changes in detail.
2. Related issue(s): Please link to the issue.
3. What kind of change does this PR introduce?: Select from template options.
4. Does this PR introduce a breaking change?: Select Yes/No.
5. How has This been tested?: Please describe how you tested your changes.
6. Screenshots (if appropriate):

**The PR fulfills these requirements:**

- [ ] It's submitted to the `main` branch.
- [ ] When resolving a specific issue, it's referenced in the related issue section above.
- [ ] My change requires a change to the documentation. Please provide a draft of the changes to the documentation.

* Be sure to check the "Allow edits from maintainers" option while creating your PR.

Be sure to fill the PR Template accordingly.
We encourage that you do a self-review prior to requesting a review. To do a self review click the review button in the top right corner, go through your code and annotate your changes. This makes it easier for the reviewer to process your PR.
