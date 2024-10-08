import subprocess
import os
import time
from service.lang_execution_class.code_execution import CodeExecution

class JavaExecution(CodeExecution):
    def __init__(self, user_id, quiz_id):
        self.user_id = user_id
        self.quiz_id = quiz_id
        self.java_dir = None

    def compile_code(self, source_code: str, unique_id: str) -> bool:
        self.java_dir = f"java_files/java_files_{unique_id}"
        # 디렉터리가 존재하지 않으면 생성
        os.makedirs(self.java_dir, exist_ok=True)
        java_filename = f"{self.java_dir}/Main.java"
        
        # 파일에 소스 코드 작성
        with open(java_filename, 'w', encoding='utf-8') as f:
            f.write(source_code)
        
        compile_process = subprocess.Popen(
            f"javac {java_filename}", 
            text=True, 
            stdout=subprocess.PIPE, 
            stderr=subprocess.PIPE, 
            shell=True
        )
        _, compile_stderr = compile_process.communicate()
        return not compile_stderr  # 컴파일 에러가 없으면 True 반환

    def run_code(self, inputs):
        process = subprocess.Popen(
            f"java -cp {self.java_dir} Main", 
            text=True, 
            stdin=subprocess.PIPE, 
            stdout=subprocess.PIPE, 
            stderr=subprocess.PIPE, 
            shell=True
        )

        stdout, stderr = process.communicate(input=inputs, timeout=5)
        return stdout.strip(), stderr.strip()

    def cleanup(self):
        if self.java_dir and os.path.exists(self.java_dir):
            # 디렉터리 내의 파일 삭제
            for file in os.listdir(self.java_dir):
                file_path = os.path.join(self.java_dir, file)
                if os.path.isfile(file_path):
                    os.remove(file_path)
            
            # 디렉터리 삭제 (비어있을 경우)
            os.rmdir(self.java_dir)