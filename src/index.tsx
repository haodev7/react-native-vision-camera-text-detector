import { type Frame, VisionCameraProxy } from 'react-native-vision-camera';

const plugin = VisionCameraProxy.initFrameProcessorPlugin('detectText', {
  model: 'fast',
});

/**
 * Scans texts.
 */
export function detectText(frame: Frame): { text: string } | null {
  'worklet';
  if (plugin == null)
    throw new Error('Failed to load Frame Processor Plugin "detectText"!');
  return plugin.call(frame) as { text: string } | null;
}
