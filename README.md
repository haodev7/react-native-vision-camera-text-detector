# react-native-vision-camera-text-detector

A React Native Vision Camera plugin for real-time text detection.  
This package enables seamless integration of on-device OCR by using:

- **Google ML Kit** on **Android**
- **Vision Framework** on **iOS**

It provides fast, efficient, and cross-platform text detector capabilities directly within Vision Camera.

---

## ✨ Features

- Real-time text detector
- On-device processing (no network required)
- Cross-platform support (Android & iOS)
- High performance with native APIs
- Easy integration with `react-native-vision-camera`

---

## 📦 Installation

### 1. Install the plugin

```bash
yarn add react-native-vision-camera-text-detector
```

## Usage

### 1. Importing

```javascript
import { detectText } from 'react-native-vision-camera-text-detector';
```

### 2. Used for frame processor

```javascript
const frameProcessor = useFrameProcessor((frame) => {
  'worklet';
  const data = detectText(frame);
  const { text } = data || {};
  console.log(text);
}, []);
```

## 💖 Support This Project

If you find this library useful and would like to support ongoing development, please consider sponsoring me on GitHub:

👉 [**Become a Sponsor**](https://github.com/sponsors/haodev7)

Your support helps me maintain and improve this project. Thank you! 🙏
