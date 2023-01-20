export const IS_DARK = false;

export function displayMode(mode: boolean) {
  return {
    type: IS_DARK,
    mode,
  };
}
