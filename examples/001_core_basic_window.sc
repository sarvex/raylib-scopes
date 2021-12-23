#!/usr/bin/env scopes

let rl = (import ..src.raylib)
using rl.macros

let
    screen_width = 800
    screen_height = 450

do-window:
    screen_width
    screen_height
    "raylib [core] Scopes example - core basic window"
    60

    do-draw:

        rl.ClearBackground rl.Colors.RAYWHITE

        rl.DrawText
            "Congrats! You created your first window!"
            190
            200
            20
            rl.Colors.LIGHTGRAY
