#!/usr/bin/env scopes

using import struct
using import Array

let rl = (import raylib.raylib)
using rl.macros

## Screen
let
    SCREEN_WIDTH = 800
    SCREEN_HEIGHT = 800
    FPS = 60


## Game constants

let
    BACKGROUND_COLOR = rl.Colors.LIGHTGRAY
    BALL_SPEED = 350.0
    BALL_RADIUS = 10
    BALL_COLOR = rl.Colors.WHITE
    PADDLE_SPEED = 200.0
    PADDLE_WIDTH = 10
    PADDLE_LENGTH = 100
    PADDLE_ARENA_BUFFER = 10
    PLAYER_1_COLOR = rl.Colors.RED
    PLAYER_2_COLOR = rl.Colors.BLUE
    ARENA_BORDER_THICKNESS = 10
    ARENA_BORDER_COLOR = rl.Colors.WHITE
    ARENA_BACKGROUND_COLOR = rl.Colors.BLACK

## Structs

struct Paddle
    position : rl.Vector2
    width : f32
    length : f32
    speed : f32

struct Paddle_Sprite
    rect : rl.Rectangle
    color : rl.Color

struct Ball
    position : rl.Vector2
    velocity : rl.Vector2
    radius : f32

struct Ball_Sprite
    position : rl.Vector2
    radius : f32
    color : rl.Color

struct Arena
    position : rl.Vector2
    width : f32
    height : f32
    wall-thickness : i32

struct Arena_Sprite
    rect : rl.Rectangle
    border-thickness : f32
    border-color : rl.Color
    background-color : rl.Color

struct Scene
    arena : Arena
    player1 : Paddle
    player2 : Paddle
    ball : Ball

## Math

fn length_Vector2 (vec)
    sqrt ((vec.x * vec.x) + (vec.y * vec.y))

## Functions
fn update-ball (delta ball)

    # calculate the new velocity if necessary

    # first detect collisions with paddles
    # for paddle in 

    # apply the velocity to the position
    (ball . position . x) += (ball . velocity . x)
    (ball . position . y) += (ball . velocity . y)

fn update-paddle (delta paddle)

    if (rl.IsKeyDown rl.KEY_UP)
        (paddle . position . y) -= (paddle.speed * delta)

    elseif (rl.IsKeyDown rl.KEY_DOWN)
        (paddle . position . y) += (paddle.speed * delta)


# fn update-scene (delta scene)

fn draw-paddle (paddle paddle_sprite)

    # update the sprite
    (paddle_sprite . rect . x) = (paddle . position . x)
    (paddle_sprite . rect . y) = (paddle . position . y)

    # then draw the sprite
    rl.DrawRectangleRec
        paddle_sprite.rect
        paddle_sprite.color

fn draw-ball (ball ball_sprite)

    # update the sprite
    (ball_sprite . position . x) = (ball . position . x)
    (ball_sprite . position . y) = (ball . position . y)

    # draw the sprite
    rl.DrawCircleV
        ball_sprite.position
        ball_sprite.radius
        ball_sprite.color

fn draw-arena (arena arena_sprite)

    # update the sprite
    (arena_sprite . rect . x) = (arena . position . x)
    (arena_sprite . rect . y) = (arena . position . y)

    # draw the arena
    rl.DrawRectangleRec
        arena_sprite.rect
        arena_sprite.background-color

    rl.DrawRectangleLinesEx
        arena_sprite.rect
        arena_sprite.border-thickness
        arena_sprite.border-color

# fn draw-scene (scene)

## Scene

local arena =
    Arena
        (position = (rl.Vector2 0.0 0.0))
        (width = SCREEN_WIDTH)
        (height = SCREEN_HEIGHT)
        (wall-thickness = ARENA_BORDER_THICKNESS)

local paddle1 =
    Paddle
        position =
            rl.Vector2
                (arena . position . x) + (arena.wall-thickness as f32) + PADDLE_ARENA_BUFFER
                ((arena . position . y) + (arena.height / 2)) - (PADDLE_LENGTH / 2)
        PADDLE_WIDTH
        PADDLE_LENGTH
        PADDLE_SPEED

local paddle2 =
    Paddle
        position =
            rl.Vector2
                ((arena . position . x) + arena.width) - (arena.wall-thickness as f32) - PADDLE_ARENA_BUFFER - PADDLE_WIDTH
                ((arena . position . y) + (arena.height / 2)) - (PADDLE_LENGTH / 2)
        PADDLE_WIDTH
        PADDLE_LENGTH
        PADDLE_SPEED

let SCREEN_CENTER =
    rl.Vector2
        (SCREEN_WIDTH / 2)
        (SCREEN_HEIGHT / 2)


local ball =
    Ball
        (position = SCREEN_CENTER)
        velocity =
            rl.Vector2 -3.0 0.0
        BALL_RADIUS



local paddle1_sprite =
    Paddle_Sprite
        rl.Rectangle
            (paddle1 . position . x) + (paddle1.width / 2)
            (paddle1 . position . y) + (paddle1.width / 2)
            paddle1.width
            paddle1.length
        PLAYER_1_COLOR

local paddle2_sprite =
    Paddle_Sprite
        rl.Rectangle
            (paddle2 . position . x)
            (paddle2 . position . y)
            paddle2.width
            paddle2.length
        PLAYER_2_COLOR

local ball_sprite =
    Ball_Sprite
        ball.position
        BALL_RADIUS
        BALL_COLOR

local arena_sprite =
    Arena_Sprite
        rect =
            rl.Rectangle
                (arena . position . x)
                (arena . position . y)
                arena.width
                arena.height
        border-thickness = ARENA_BORDER_THICKNESS
        border-color = ARENA_BORDER_COLOR
        background-color = ARENA_BACKGROUND_COLOR

# local scene =
#     Scene
        


# camera
local camera =
    rl.Camera2D
        (rl.Vector2 (SCREEN_WIDTH / 2.0) (SCREEN_HEIGHT / 2.0))
        (rl.Vector2 (SCREEN_WIDTH / 2.0) (SCREEN_HEIGHT / 2.0))
        0.0:f32
        1.0:f32

do-window:
    SCREEN_WIDTH
    SCREEN_HEIGHT
    "Pong"
    FPS

    local delta-time = ((rl.GetFrameTime) as f32)

    # (update-positions)
    # (update-scene)

    do-draw:

        rl.ClearBackground BACKGROUND_COLOR

        (update-ball delta-time ball)
        (update-paddle delta-time paddle1)
        # (update-paddle delta-time paddle2)

        # Do 2D drawing
        rl.BeginMode2D camera

        draw-arena arena arena_sprite

        draw-paddle paddle1 paddle1_sprite
        draw-paddle paddle2 paddle2_sprite
        draw-ball ball ball_sprite

        rl.EndMode2D;
