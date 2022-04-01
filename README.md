# scopes-raylib

Bindings of [Raylib](https://github.com/raysan5/raylib) for the
[Scopes](https://scopes.rocks) programming language.

This is an incredibly thin wrapper as such and you can basically use
the Raylib C-API with Scopes notation. Some of the naming prefixes
have been scrubbed to make calling things less verbose.

There are a few macros added for "begin-end" type constructs that you
can see in use in the examples, but you don't need to use them.


## Installation

The module is under `src/raylib`. You can copy this subtree into your
project and then add it to the `package.path` in your Scopes
`_project.sc` file.

### With Spack

This module is available as the `scopes-raylib` package in the
[snailpacks](https://github.com/salotz/snailpacks) repository. This will pull in the necessary dependencies
including Scopes.

```sh
  spack install scopes-raylib
```

See the [snailpacks](https://github.com/salotz/snailpacks) documentation for more best practices of installing.

## Development Environment

We use [Spack](https://spack.io/) to install dependencies. First install Spack.

Then you'll need our custom repo of build recipes:

```sh
  mkdir -p `/.spack/repos
  git clone git@github.com:salotz/snailpacks.git `/.spack/repos/snailpacks
  spack repo add `/resources/spack-repos/snailpacks
```

Then you need to create an environment in this folder that will
contain the headers and libraries etc.

```sh
  spack env create -d .
```

Activate the environment (i.e. set the environment variables
appropriately) and install the packages:

```sh
  spacktivate .
  spack install
```

To exit the environment (i.e. unset the env variables):

```sh
  despacktivate
```
