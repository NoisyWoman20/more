from PIL import Image

def add_color_tint(input_path, output_path, hex_color="#3F76E4", intensity=0.5):
    """
    为图像添加指定颜色的色调
    
    参数:
        input_path: 输入图片路径
        output_path: 输出图片路径
        hex_color: 要添加的十六进制颜色 (默认 #3F76E4)
        intensity: 颜色强度 (0.0 - 1.0, 默认 0.5)
    """
    # 将十六进制颜色转换为RGB值
    hex_color = hex_color.lstrip('#')
    rgb = tuple(int(hex_color[i:i+2], 16) for i in (0, 2, 4))
    
    # 打开图像并转换为RGBA模式
    image = Image.open(input_path).convert("RGBA")
    
    # 创建彩色滤镜图层
    tint = Image.new("RGBA", image.size, rgb)
    
    # 根据强度调整滤镜图层的不透明度
    alpha = int(255 * intensity)
    tint.putalpha(alpha)
    
    # 使用alpha_composite混合原图和滤镜
    result = Image.alpha_composite(image, tint)
    
    # 保存结果
    result.save(output_path)
    print(f"处理完成！已添加 {hex_color} 色调，强度: {intensity*100}%")
    return result

# 使用示例
if __name__ == "__main__":
    # 添加 #3F76E4 色调，强度50%
    add_color_tint("./water_top_and_bottom_0.png", "./water_top_and_bottom.png", "#2C529E", 0.65)
    add_color_tint("./water_side_0.png", "./water_side.png", "#2C529E", 0.65)
