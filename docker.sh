docker build -t ryan3369/open-ai-promt .
docker tag ryan3369/open-ai-promt:latest public.ecr.aws/r3p0h4n8/openai:latest
docker push public.ecr.aws/r3p0h4n8/openai:latest
# docker push ryan3369/open-ai-promt:latest
# docker run -p 8080:8080 ryan3369/open-ai-promt:latest