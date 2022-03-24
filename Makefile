.spack-env: spack.yaml
	spack env create -d .
	spack install

clean:
	rm -rf .spack-env
.PHONY: clean

