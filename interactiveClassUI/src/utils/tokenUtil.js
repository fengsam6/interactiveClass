import {getStorage, setStorage} from './storage.js'

const token_key = "USER-TOKEN"

export function getToken() {
    return getStorage(token_key)
}

export function setToken(token) {
    return setStorage(token_key, token)
}