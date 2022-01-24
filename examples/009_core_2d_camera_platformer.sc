#!/usr/bin/env scopes

using import struct
using import Array

let rl = (import ..src.raylib)
using rl.macros

## Screen
let
    SCREEN_WIDTH = 800
    SCREEN_HEIGHT = 450
    G = 400
    PLAYER_JUMP_SPEED = 350.0:f32
    PLAYER_HOR_SPEED = 200.0:f32


## Structs
struct Player
    position : rl.Vector2
    speed : f32
    can-jump : bool

struct EnvItem
    rect : rl.Rectangle
    blocking : i32
    color : rl.Color

## Update functions
fn update_player (player env-items delta)

    if (rl.IsKeyDown rl.KEY_LEFT)
        ((player . position) . x) -= (PLAYER_HOR_SPEED * delta)

    if (rl.IsKeyDown rl.KEY_RIGHT)
        ((player . position) . x) += (PLAYER_HOR_SPEED * delta)

    if ((rl.IsKeyDown rl.KEY_SPACE) and (player.can-jump))
        (player . speed) = -PLAYER_JUMP_SPEED
        (player . can-jump) = false

    local hit-obstacle = 0
    for env-item in env-items

        let player-position = (player . position)
        if (env-item.blocking and
            ((((env-item . rect) . x) + ((env-item . rect) . width)) >= player-position.x) and
            (((env-item . rect) . y) >= player-position.y) and
            (((env-item . rect) . y) < player-position.y + (player.speed * delta))
        )
            hit-obstacle = 1
            (player . speed) = 0.0:f32
            (player-position . y) = ((env-item . speed) . y)

    if (not hit-obstacle)
        ((player . position) . y) += (player.speed * delta)
        (player . speed) += (G * delta)
        (player . can-jump) = false
    else
        (player . can-jump) = true

fn update-camera-center ()
    

fn update-camera-center-inside-map ()
    

fn update-camera-even-out-on-landing ()
    

fn update-camera-player-bounds-push ()
    



## Main

local player =
    Player
        (position = (rl.Vector2 400 280))
        (speed = 0:f32)
        (can-jump = false)

local env-items =
    (Array EnvItem)
        EnvItem
            (rect = (rl.Rectangle 0 0 1000 400))
            (blocking = 0)
            (color = rl.Colors.LIGHTGRAY)
        EnvItem
            (rl.Rectangle 0 400 1000 200)
            1
            rl.Colors.GRAY
        EnvItem
            (rl.Rectangle 300 200 400 10)
            1
            rl.Colors.GRAY
        EnvItem
            (rl.Rectangle 250 300 100 10)
            1
            rl.Colors.GRAY
        EnvItem
            (rl.Rectangle 650 300 100 10)
            1
            rl.Colors.GRAY

let env-items-len = (countof env-items)
## Camera

let camera-update-funcs =
    Array 
        

local camera-option = 0
local campera-updaters-length = 

local camera =
    rl.Camera2D
        player.position
        (rl.Vector2 (SCREEN_WIDTH / 2.0) (SCREEN_HEIGHT / 2.0))
        0.0:f32
        1.0:f32

let camera-descriptions =
    Array string
        "Follow player center"
        "Follow player center, but clamp to map edges"
        "Follow player center; smoothed"
        "Follow player center horizontally; updateplayer center vertically after landing"
        "Player push camera on getting too close to screen edge"

do-window:
    SCREEN_WIDTH
    SCREEN_HEIGHT
    "raylib [core] Scopes example - core basic window"
    60

    local delta-time = rl.GetFrameTime;

    (update_player player env-items env-items-len delta-time)

    do-draw:

        rl.ClearBackground rl.Colors.LIGHTGRAY
