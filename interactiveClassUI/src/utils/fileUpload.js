import {imgUploadUrl,imgAccessUrl} from "./config";
export const imgHttpBaseUrl = imgAccessUrl
export function chooseAndUploadImage(){
    return new Promise((resolve, reject)=>{
        uni.chooseImage({
            success: (chooseImageRes) => {
                const tempFilePaths = chooseImageRes.tempFilePaths;
                uni.uploadFile({
                    count: 1,
                    sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
                    sourceType: ['album'], //从相册选择
                    url: imgUploadUrl, //图片上传地址
                    filePath: tempFilePaths[0],
                    name: 'file',
                    success: (uploadFileRes) => {
                        const resp = uploadFileRes.data
                        const respObj = JSON.parse(resp)
                        let imgUrl = respObj.data
                        debugger
                        console.log(imgUrl);
                        let imgHttpUrl = imgAccessUrl+imgUrl
                        console.log(imgHttpUrl)
                        resolve(imgUrl)
                    },
                    fail:(error)=>{
                        console.log(error);
                        reject(error)
                    }
                });
            }
        });
    })


}
