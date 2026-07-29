export const Commands = {
  TURN_ON: "TURN_ON",
  TURN_OFF: "TURN_OFF",

  TRIGGER_MOTION: "TRIGGER_MOTION",
  RESET_MOTION: "RESET_MOTION",

  INCREASE_TEMPERATURE: "INCREASE_TEMPERATURE",
  DECREASE_TEMPERATURE: "DECREASE_TEMPERATURE",
} as const;

export type Command = (typeof Commands)[keyof typeof Commands];
