// 本地开发url
const url_base = process.env.VUE_APP_BASE_API
const fileUploadUrl = url_base + '/file/upload'
const imgUploadUrl = url_base + '/file/uploadImg'
const imgAccessUrl = url_base + '/img/'
const downUrl = url_base + '/file/download?filePath='
export function downFile(filePath) {
  window.open(downUrl + filePath, '_blank')
}
export default {
  fileUploadUrl,
  imgUploadUrl,
  imgAccessUrl
}
