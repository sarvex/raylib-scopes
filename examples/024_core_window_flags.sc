let rl = (import raylib.raylib)
using rl.macros

let
    screen_width = 800
    screen_height = 450

local ball-position =
    rl.Vector2
        (rl.GetScreenWidth; as f32) / 2.0
        (rl.GetScreenHeight; as f32) / 2.0

local ball-speed =
    rl.Vector2
        5.0
        4.0

local ball-radius = 20.0

local frames-counter = 0

do-window:
    screen_width
    screen_height
    "raylib [core] Scopes example - window flags"
    60

    # Window settings
    if (rl.IsKeyPressed rl.KEY_F)
        rl.ToggleFullscreen;

    # Toggle window resizable
    if (rl.IsKeyPressed rl.KEY_R)

        if (rl.IsWindowState rl.FLAG_WINDOW_RESIZABLE)
            rl.ClearWindowState rl.FLAG_WINDOW_RESIZABLE

        else
            rl.SetWindowState rl.FLAG_WINDOW_RESIZABLE

    # Toggle window resizable
    if (rl.IsKeyPressed rl.KEY_D)

        if (rl.IsWindowState rl.FLAG_WINDOW_UNDECORATED)
            rl.ClearWindowState rl.FLAG_WINDOW_UNDECORATED

        else
            rl.SetWindowState rl.FLAG_WINDOW_UNDECORATED

    # Toggle window hidden
    if (rl.IsKeyPressed rl.KEY_H)

        if (not (rl.IsWindowState rl.FLAG_WINDOW_HIDDEN))
            rl.SetWindowState rl.FLAG_WINDOW_HIDDEN

        frames-counter = 0

    # unhide after a few frames
    if (rl.IsWindowState rl.FLAG_WINDOW_HIDDEN)
        frames-counter += 1
        if (frames-counter >= 240)
            rl.ClearWindowState rl.FLAG_WINDOW_HIDDEN

    # minimize the window
    if (rl.IsKeyPressed rl.KEY_N)

        if (rl.IsWindowState rl.FLAG_WINDOW_MINIMIZED)
            rl.MinimizeWindow;
        frames-counter = 0

    # unminimize after a few frames
    if (rl.IsWindowState rl.FLAG_WINDOW_MINIMIZED)
        frames-counter += 1
        if (frames-counter >= 240)
            rl.ClearWindowState rl.FLAG_WINDOW_MINIMIZED



    ball-position.x += ball-speed.x
    ball-position.y += ball-speed.y

    if ((ball-position.x >= ((rl.GetScreenWidth; as f32) - ball-radius)) or
        (ball-position.x <= ball-radius)
    )
        ball-speed.x *= -1.0

    if ((ball-position.y >= ((rl.GetScreenWidth; as f32) - ball-radius)) or
        (ball-position.y <= ball-radius)
    )
        ball-speed.y *= -1.0


    do-draw:
        rl.ClearBackground rl.Colors.RAYWHITE

        rl.DrawCircleV ball-position ball-radius rl.Colors.MAROON
        # rl.DrawRectangleLinesEx 

        rl.DrawFPS 10 10

        rl.DrawText
            "Screen Size:" .. (tostring (rl.GetScreenWidth)) .. (tostring (rl.GetScreenHeight))
            10
            40
            10
            rl.Colors.GREEN

        rl.DrawText
            "Following flags can be set after window creation:"
            10
            60
            10
            rl.Colors.GRAY



        rl.DrawText
            "Following flags can only be set before window creation:"
            10
            60
            10
            rl.Colors.GRAY
