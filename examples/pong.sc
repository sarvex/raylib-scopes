#!/usr/bin/env scopes

using import struct
using import Array

let rl = (import ..src.raylib)
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
    BALL_RADIUS = 20
    PADDLE_SPEED = 200.0
    PADDLE_WIDTH = 10
    PADDLE_LENGTH = 50

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
    speed : f32
    radius : f32

struct Ball_Sprite
    position : rl.Vector2
    radius : f32
    color : rl.Color

struct Arena
    width : f32
    height : f32
    color : f32

struct Scene
    arena : Arena
    player1 : Paddle
    player2 : Paddle
    ball : Ball

fn update-scene (delta scene)

fn update-ball (delta ball)

fn draw-paddle (paddle_sprite)

    rl.DrawRectangleRec
        paddle_sprite.rect
        paddle_sprite.color

fn draw-ball (ball_sprite)
    rl.DrawCircleV
        ball_sprite.position
        ball_sprite.radius
        ball_sprite.color

## Starting data

local paddle1 =
    Paddle
        position =
            rl.Vector2 10.0 (SCREEN_HEIGHT / 2)
        PADDLE_WIDTH
        PADDLE_LENGTH
        PADDLE_SPEED

local paddle2 =
    Paddle
        position =
            rl.Vector2 (SCREEN_WIDTH - PADDLE_WIDTH - 10.0) (SCREEN_HEIGHT / 2)
            # rl.Vector2 (SCREEN_WIDTH - 10.0) (SCREEN_HEIGHT - 10.0)
        PADDLE_WIDTH
        PADDLE_LENGTH
        PADDLE_SPEED

local ball =
    Ball
        position =
            rl.Vector2 (SCREEN_WIDTH / 2) (SCREEN_HEIGHT / 2)
        BALL_SPEED
        BALL_RADIUS

local paddle1_sprite =
    Paddle_Sprite
        rl.Rectangle
            # TODO: center the paddles
            (paddle1 . position . x) + (paddle1.width / 2)
            (paddle1 . position . y) + (paddle1.width / 2)
            paddle1.width
            paddle1.length
        rl.Colors.RED

local paddle2_sprite =
    Paddle_Sprite
        rl.Rectangle
            (paddle2 . position . x)
            (paddle2 . position . y)
            paddle2.width
            paddle2.length
        rl.Colors.BLUE

local ball_sprite =
    Ball_Sprite
        ball.position
        BALL_RADIUS
        rl.Colors.WHITE

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

        rl.ClearBackground rl.Colors.LIGHTGRAY

        # Do 2D drawing
        rl.BeginMode2D camera

        # draw the paddles
        draw-paddle paddle1_sprite
        draw-paddle paddle2_sprite
        draw-ball ball_sprite

        rl.EndMode2D;
