import config from "./config";
export const uploadUrl = config.url_base+"/file/upload"
export function chooseAndUploadImage(){
    return new Promise((resolve, reject)=>{
        uni.chooseImage({
            success: (chooseImageRes) => {
                const tempFilePaths = chooseImageRes.tempFilePaths;
                uni.uploadFile({
                    url: uploadUrl, //仅为示例，非真实的接口地址
                    filePath: tempFilePaths[0],
                    name: 'file',
                    success: (uploadFileRes) => {
                        console.log(uploadFileRes.data);
                        resolve(uploadFileRes.data)
                    },
                    fail:(error)=>{
                        reject(error)
                    }
                });
            }
        });
    })

}
