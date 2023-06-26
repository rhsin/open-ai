docker build -t ryan3369/open-ai-promt .
docker tag ryan3369/open-ai-promt:latest 587609106891.dkr.ecr.us-east-2.amazonaws.com/openai-prompt:latest
docker push 587609106891.dkr.ecr.us-east-2.amazonaws.com/openai-prompt:latest
# docker push ryan3369/open-ai-promt:latest
# docker run -p 8080:8080 ryan3369/open-ai-promt:latest