"""Helper untuk membuat 'screenshot' tampilan terminal (PNG) dari teks output."""

from PIL import Image, ImageDraw, ImageFont

FONT_PATH = "/usr/share/fonts/truetype/dejavu/DejaVuSansMono.ttf"
FONT_SIZE = 16
PADDING = 20
BG_COLOR = (12, 12, 12)
FG_COLOR = (0, 255, 0)
TITLE_BAR_COLOR = (48, 48, 48)
TITLE_HEIGHT = 32


def render(text_lines, out_path, title="Terminal - toko_retail"):
    font = ImageFont.truetype(FONT_PATH, FONT_SIZE)

    dummy_img = Image.new("RGB", (10, 10))
    dummy_draw = ImageDraw.Draw(dummy_img)
    line_widths = []
    line_height = 0
    for line in text_lines:
        bbox = dummy_draw.textbbox((0, 0), line if line else " ", font=font)
        line_widths.append(bbox[2] - bbox[0])
        line_height = max(line_height, bbox[3] - bbox[1])

    line_spacing = int(line_height * 1.5)
    width = max(line_widths) + PADDING * 2 if line_widths else 400
    height = TITLE_HEIGHT + PADDING * 2 + line_spacing * len(text_lines)

    img = Image.new("RGB", (width, height), BG_COLOR)
    draw = ImageDraw.Draw(img)

    # Title bar
    draw.rectangle([0, 0, width, TITLE_HEIGHT], fill=TITLE_BAR_COLOR)
    # traffic light dots
    for i, color in enumerate([(255, 95, 86), (255, 189, 46), (39, 201, 63)]):
        draw.ellipse([12 + i * 20, 11, 22 + i * 20, 21], fill=color)
    title_font = ImageFont.truetype(FONT_PATH, 14)
    draw.text((width / 2 - len(title) * 3.5, 8), title, font=title_font, fill=(220, 220, 220))

    y = TITLE_HEIGHT + PADDING
    for line in text_lines:
        draw.text((PADDING, y), line, font=font, fill=FG_COLOR)
        y += line_spacing

    img.save(out_path)
    print(f"Saved: {out_path}")
