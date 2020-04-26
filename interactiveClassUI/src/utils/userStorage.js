import {getStorage, setStorage} from '@/utils/storage'

const userStoreKey = "user"

export  function saveUserStore(userInfo) {
    setStorage(userStoreKey, userInfo)
}

export function getStoreUser() {
    return getStorage(userStoreKey)
}
