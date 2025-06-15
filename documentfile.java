public class DocumentFileExample {
    private static final int REQUEST_CODE_OPEN_DIRECTORY = 1;
    
    public void createDocumentFile(Activity activity) {
        // 请求用户选择目录
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        activity.startActivityForResult(intent, REQUEST_CODE_OPEN_DIRECTORY);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data, Context context) {
        if (requestCode == REQUEST_CODE_OPEN_DIRECTORY && resultCode == Activity.RESULT_OK) {
            Uri treeUri = data.getData();
            
            // 获取持久化权限
            context.getContentResolver().takePersistableUriPermission(
                treeUri, 
                Intent.FLAG_GRANT_READ_URI_PERMISSION | 
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            );
            
            // 创建 DocumentFile 表示所选目录
            DocumentFile directory = DocumentFile.fromTreeUri(context, treeUri);
            
            if (directory != null && directory.exists()) {
                // 创建新文件
                DocumentFile newFile = directory.createFile("text/plain", "example.txt");
                
                // 写入内容
                try (OutputStream out = context.getContentResolver().openOutputStream(newFile.getUri())) {
                    out.write("This is a test file created with DocumentFile".getBytes());
                    Toast.makeText(context, "文件创建成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(context, "文件创建失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }
}
