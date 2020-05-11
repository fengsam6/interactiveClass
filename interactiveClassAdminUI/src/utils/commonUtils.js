export function strIsNotNull(str) {
  return !strIsNull(str)
}

export function strIsNull(str) {
  if (str === undefined || str === null || str === '') {
    return true
  }
  return false
}

export function getFileName(filePath) {
  let fileName = filePath
  if (strIsNull(filePath)) {
    return null
  }
  if (filePath.contain('_')) {
    fileName = filePath.substr(filePath.lastIndexOf('_') + 1)
  }
  return fileName
}

export function getDefaultPage() {
  return {
    pageSize: 20,
    curPage: 1
  }
}
