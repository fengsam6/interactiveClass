/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

export function isNotNull(str) {
  return str !== undefined && str !== null && str.trim() !== ''
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  return isNotNull(str)
}
