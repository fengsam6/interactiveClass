export function errorAlert(msg){
    uni.showToast({
        title: msg,
        image: '/static/icon/error.png',
        duration: 5000
    });
}

export function successAlert(msg){
    uni.showToast({
        title: msg,
        duration: 4000
    });
}