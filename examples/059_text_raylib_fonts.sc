#!/usr/bin/env scopes

using import Array
using import itertools
using import struct

let rl = (import ..src.raylib)
using rl.macros

struct FontDisplay
    id : string
    name : string
    author : string
    spacing : f32
    color : rl.Color

let MAX_FONTS = 8

fn log (msg)
    (rl.TraceLog 3 (.. "===> " msg))

let
    screen_width = 800
    screen_height = 450

let font_path = "resources/fonts/"
let font_ext = ".png"

## configure the fonts, text and placement

let specs =
    (Array FontDisplay)
        (FontDisplay "alagard" "ALAGARD" "Hewett Tsoi" 2.0 rl.Colors.MAROON)
        (FontDisplay "pixelplay" "PIXELPLAY" "Aleksander Shevchuk" 4.0 rl.Colors.ORANGE)
        (FontDisplay "mecha" "MECHA" "Captain Falcon" 8.0 rl.Colors.DARKGREEN)
        (FontDisplay "setback" "SETBACK" "Brian Kent (Aenigma)" 4.0 rl.Colors.DARKBLUE)
        (FontDisplay "romulus" "ROMULUS" "Hewett Tsoi" 3.0 rl.Colors.DARKPURPLE)
        (FontDisplay "pixantiqua" "PIXANTIQUA" "Gerhard Grossman" 4.0 rl.Colors.LIME)
        (FontDisplay "alpha_beta" "ALPHA_BETA" "Brian Kent (Aenigma)" 4.0 rl.Colors.GOLD)
        FontDisplay
            "jupiter_crash"
            "JUPITER_CRASH"
            "Brian Kent (Aenigma)"
            1.0
            rl.Colors.RED


rl.InitWindow
    screen_width
    screen_height
    "raylib [text] Scopes example - raylib fonts"

rl.SetTargetFPS 60

# calculate the positions for each font based on the spacing and text size
local positions = ((Array rl.Vector2))
local fonts = ((Array rl.Font))
local messages = ((Array string))

for spec_idx spec in (zip (range (countof specs)) specs)

    # for i in (range (countof fonts))
    log (.. "font number: " (tostring spec_idx))

    let font = (rl.LoadFont (.. font_path spec.id font_ext))

    let message = (.. spec.name " FONT designed by " spec.author)

    let text_extent =
        rl.MeasureTextEx
            font
            message
            ((font.baseSize as f32) * 2.0:f32)
            spec.spacing


    let x = (((screen_width as f32) / 2.0:f32) - (text_extent.x / 2.0:f32))
    let y = (60.0:f32 + (font.baseSize as f32) + (45.0:f32 * (spec_idx as f32)))

    'append positions (rl.Vector2 x y)
    'append fonts font
    'append messages message


while (not (rl.WindowShouldClose))

    do-draw:

        rl.ClearBackground rl.Colors.RAYWHITE

        (rl.DrawText "Free fonts included with raylib" 250 20 20 rl.Colors.DARKGRAY)
        (rl.DrawLine 220 50 590 50 rl.Colors.DARKGRAY)

        for spec font position message in (zip specs fonts positions messages)

            let font_size = (((font . baseSize) as f32) * 2.0:f32)

            rl.DrawTextEx
                font
                message
                position
                font_size
                spec.spacing
                spec.color

for font in fonts
    rl.UnloadFont font

rl.CloseWindow;
